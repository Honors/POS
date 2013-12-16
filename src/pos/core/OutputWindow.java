package pos.core;

public interface OutputWindow {
	public void writeToOutput(String s);
	public void clearOutput();
	public void update(String command);
}
