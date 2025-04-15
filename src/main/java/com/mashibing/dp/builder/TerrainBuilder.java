package com.mashibing.dp.builder;

public interface TerrainBuilder {
    //返回的还是Builder，跟StringBuilder可以一直append同理
    TerrainBuilder buildWall();
    TerrainBuilder buildFort();
    TerrainBuilder buildMine();
    Terrain build();
}
