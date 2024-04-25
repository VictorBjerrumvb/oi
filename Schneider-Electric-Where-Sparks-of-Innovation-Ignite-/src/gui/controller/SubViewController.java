package gui.controller;

/**
 * Abstract class for sub-controllers to provide common functionalities and
 * a reference to the main controller.
 */
public abstract class SubViewController {

    protected MainViewController mainController;

    /**
     * Sets the main application controller.
     *
     * @param mainController the main controller of the application
     */
    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
    }

    /**
     * Optional initialize method to be overridden by subclasses for specific setups.
     */
    public void initialize() {
        // Default implementation, can be overridden
    }
}
