package j2html.model;

public class PageModel {

  private String title;
  private String text;
  private ButtonModel buttonModel;

  public PageModel(String title, String text, ButtonModel buttonModel) {
    this.title = title;
    this.text = text;
    this.buttonModel = buttonModel;
  }

  public ButtonModel getButtonModel() {
    return buttonModel;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }
}
