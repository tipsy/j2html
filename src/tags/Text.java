package javaHtmlGenerator.tags;

public class Text extends BaseTag {

	public Text(String text) {
		super(text);
	}
	
	@Override
	public String render(){
		return tag;
	}
	
	@Override
	public String toString(){
		return this.render();
	}
	
}
