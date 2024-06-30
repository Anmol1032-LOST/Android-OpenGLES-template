package com.anmol.brokenglass.game.core.map;

import android.opengl.Matrix;

import com.anmol.brokenglass.game.Renderer;

public class MapRenderer implements Renderer {
    private Square mSquare;
    private final float[] mMVPMatrix = new float[16];

    @Override
    public void init() {
        mSquare = new Square();
    }

    @Override
    public void draw(long tpf, float[] mProjectionMatrix, float[] mViewMatrix) {
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        mSquare.draw(mMVPMatrix);
    }
}
