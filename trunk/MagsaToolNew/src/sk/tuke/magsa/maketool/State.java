package sk.tuke.magsa.maketool;

import java.awt.Color;

public enum State {
    UNAVAILABLE(Color.LIGHT_GRAY), READY(Color.YELLOW), COMPLETED(Color.GREEN), ERROR(Color.RED);

    private final Color color;

    private State(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
