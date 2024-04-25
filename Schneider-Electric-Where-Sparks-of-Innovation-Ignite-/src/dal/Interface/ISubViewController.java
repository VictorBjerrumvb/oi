package dal.Interface;

import gui.controller.MainViewController;

public interface ISubViewController {
    void initializeView();
    void loadData();
    void setMainController(MainViewController controller);
    // any other common methods that your subviews should have
}
