package Data;

/**
 * Created by gveenam on 2/17/2021.
 */


//POJO- Plain Old Java Object
public class Users {

    String name;
    String job;

    public Users(){

    }

    public Users(String name,String job){
        this.name=name;
        this.job=job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }


}
