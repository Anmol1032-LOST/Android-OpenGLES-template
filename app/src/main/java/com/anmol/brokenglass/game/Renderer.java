package com.anmol.brokenglass.game;

import android.opengl.GLES20;

public interface Renderer {
    void init();
    void draw(long tpf, float[] mProjectionMatrix, float[] mViewMatrix);
}
