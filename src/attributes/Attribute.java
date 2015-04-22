package j2html.src.attributes;

public class Attribute {
	private String name;
	private String value;
	
	public Attribute(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public Attribute(String name){
		this.name = name;
		this.value = null;
	}
	
	public String render(){
		StringBuilder b = new StringBuilder(" ");
		b.append(name);
		if(value != null){
			b.append("=\"");
			b.append(value);
			b.append("\"");
		}
		return b.toString();
	}
	
	@Override
	public String toString(){
		return this.render();
	}

	public String getName() {
		return name;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
