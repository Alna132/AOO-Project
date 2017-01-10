package ie.gmit.sw;
/**
 * The main Runner Class
 */
public class Runner {
	/**
     * The main method.
     * Starts the main Swing GUI
     *
     * @param args
     * Any command line arguments. Not currently used.
     */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AppWindow();
			}//- End of run()
		});
	}//- End of main
}//- End of Runner
