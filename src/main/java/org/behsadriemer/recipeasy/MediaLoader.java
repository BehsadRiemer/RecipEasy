package org.behsadriemer.recipeasy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class MediaLoader {
    private static MediaLoader instance;

    private final HashMap<String, ImageIcon> cache;

    private MediaLoader() {
        cache = new HashMap<>();
    }

    public static MediaLoader getInstance() {
        if(instance == null) {
            instance = new MediaLoader();
        }
        return instance;
    }

    public ImageIcon loadImage(String resource) {
        if(cache.containsKey(resource)) {
            return cache.get(resource);
        } else {
            InputStream stream = getClass().getResourceAsStream(resource);
            try {
                if(stream != null) {
                    Image img = ImageIO.read(stream)
                            .getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );
                    ImageIcon loaded = new ImageIcon(img);
                    cache.put(resource, loaded);
                    return loaded;
                } else {
                    System.err.println("Empty stream for: '" + resource + "'.");
                }
            } catch(IOException e) {
                System.err.println("Error loading: '" + resource + "'!");
            }
            System.err.println("'" + resource + "' not found!");
            return null;
        }
    }
}
