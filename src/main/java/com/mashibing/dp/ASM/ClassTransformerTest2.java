package com.mashibing.dp.ASM;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

//gpt生成的，跑不通，还没解决
public class ClassTransformerTest2 {
    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader(
                ClassTransformerTest.class.getClassLoader().getResourceAsStream("com/mashibing/dp/ASM/Tank.class"));

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        ClassVisitor cv = new ClassVisitor(ASM4, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

                return new MethodVisitor(ASM4, mv) {
                    private int startTimeIndex = 1; // 0 是 this，占1个槽；long 类型占2个槽，所以 1、2

                    @Override
                    public void visitCode() {
                        super.visitCode();
                        // 插入：long start = System.currentTimeMillis();
                        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                        mv.visitVarInsn(LSTORE, startTimeIndex); // 存入局部变量
                    }

                    @Override
                    public void visitInsn(int opcode) {
                        // 判断是否是任何形式的返回指令
                        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
                            // 插入：long end = System.currentTimeMillis();
                            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                            mv.visitVarInsn(LLOAD, startTimeIndex); // 加载 start
                            mv.visitInsn(LSUB); // end - start

                            // 转为字符串打印
                            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            mv.visitInsn(SWAP);
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
                        }
                        super.visitInsn(opcode);
                    }
                };
            }
        };

        cr.accept(cv, ClassReader.EXPAND_FRAMES);
        byte[] b2 = cw.toByteArray();

        // 加载新类
        MyClassLoader cl = new MyClassLoader();
        Class<?> tankClass = cl.defineClass("com.mashibing.dp.ASM.Tank", b2);
        Object tank = tankClass.getConstructor().newInstance(); // 会执行构造方法，触发耗时统计

        // 输出到文件
        String path = System.getProperty("user.dir") + "/com/mashibing/dp/ASM/";
        File f = new File(path);
        f.mkdirs();

        FileOutputStream fos = new FileOutputStream(new File(path + "Tank_0.class"));
        fos.write(b2);
        fos.flush();
        fos.close();
    }
}

