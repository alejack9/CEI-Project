package main;

import java.io.IOException;

@FunctionalInterface
public interface Step {
	public boolean get() throws IOException;
}
