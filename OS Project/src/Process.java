public class Process {
    private String id;
    private int arr_time;
    private int ser_time;
    private int priority;
    public void setId(String id){this.id = id;}
    public void setArr_time(int arr_time){this.arr_time = arr_time;}
    public void setSer_time(int ser_time){this.ser_time = ser_time;}
    public void setPriority(int priority){this.priority = priority;}
    public String getId(){return id;}
    public int getArr_time(){return arr_time;}
    public int getSer_time(){return ser_time;}
    public int getPriority(){return priority;}
}