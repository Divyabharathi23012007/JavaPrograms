class ne{
    private String name;
    private int age;
     
    public void setName(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setAge(int age){
        this.age=age;
    }
    
    public int getAge(){
        return age;
    }
}

public class Encapsulation{
    public static void main(String [] args){
        ne o=new ne();
        o.setName("Divya");
        o.setAge(18);
        System.out.println("name is "+o.getName()+" and age is "+o.getAge());
    }
}