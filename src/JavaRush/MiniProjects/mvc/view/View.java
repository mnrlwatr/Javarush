package JavaRush.MiniProjects.mvc.view;

import JavaRush.MiniProjects.mvc.controller.Controller;
import JavaRush.MiniProjects.mvc.model.ModelData;


public interface View {
    void refresh(ModelData modelData);

    void setController(Controller controller);

}
