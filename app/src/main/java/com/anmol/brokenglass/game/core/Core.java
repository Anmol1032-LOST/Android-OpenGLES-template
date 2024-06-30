package com.anmol.brokenglass.game.core;

import com.anmol.brokenglass.game.Renderer;
import com.anmol.brokenglass.game.core.map.MapRenderer;

import java.util.ArrayList;

public class Core {
    public static void addRenderers(ArrayList<Renderer> renderers) {
        renderers.add(new MapRenderer());
    }
}
