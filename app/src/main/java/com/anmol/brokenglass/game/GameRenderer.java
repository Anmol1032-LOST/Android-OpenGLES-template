package com.anmol.brokenglass.game;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.anmol.brokenglass.game.core.Core;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameRenderer implements GLSurfaceView.Renderer {
    public int width;
    public int height;
    private long lastFrameTime;
    private int frameRendered;
    private long fpsLogTime;
    public ArrayList<Renderer> renderers = new ArrayList<>();
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private float t;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        final long t = System.currentTimeMillis();
        lastFrameTime = t;
        fpsLogTime = t;
        Core.addRenderers(renderers);
        renderers.stream().parallel().forEach(Renderer::init);
        GLES20.glClearColor(0.0f, 1.0f, 0.75f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        this.width = width;
        this.height = height;
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 1000);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        final long targetFrameTime = 13;
        frameRendered++;

        final long currentTime = System.currentTimeMillis();
        final long elapsedTime = currentTime - lastFrameTime;

        if (currentTime - fpsLogTime >= 1000) {
            final float avgFPS = frameRendered / ((currentTime - fpsLogTime) / 1000f);
            if (frameRendered >= 1000 / targetFrameTime - 4) {
                Log.d("FPS", String.valueOf(avgFPS));
            } else {
                Log.w("FPS", String.valueOf(avgFPS));
            }

            frameRendered = 0;
            fpsLogTime = currentTime;
        }

        if (elapsedTime < targetFrameTime) {
            try {
                Thread.sleep(targetFrameTime - elapsedTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        draw(elapsedTime);

        lastFrameTime = System.currentTimeMillis();
    }

    private void draw(long tpf) {
        t+=tpf/1000f;

        Matrix.setLookAtM(mViewMatrix, 0, (float) Math.cos(t)*24, 12, (float) (Math.sin(t)*24), 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        renderers.stream().parallel().forEach(renderer -> renderer.draw(tpf, mProjectionMatrix, mViewMatrix));
    }
}
