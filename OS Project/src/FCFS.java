public class FCFS extends Super{
    FCFS(Process process[], int count){
        this.process = process;
        this.count = count;
        pwt = new int[count];
        ret = new int[count];
        prt = new int[count];
        runtime = new int[count];
        for(int i = 0 ; i < count ; i++)pwt[i]=0;
        for(int i = 0 ; i < count ; i++)ret[i]=0;
        for(int i = 0 ; i < count ; i++)prt[i]=0;
    }
    public void run(){
        System.out.println("FCFS 알고리즘 실행");
        process = sort(process,1);
        for(int i = 0 ; i < count ; i++)runtime[i]=process[i].getSer_time();
        Gantt();
        Print();
    }
    public void Gantt(){
        for(int i = 0 ; i < count ; i++){
            pwt[i] = time - process[i].getArr_time();
            for(int j = 0 ; j < process[i].getSer_time() ; j++) {
                if(prt[i]==0)prt[i]=time+res_time-process[i].getArr_time();
                System.out.print(process[i].getId() + " ");
                time++;
            }
            System.out.print("| ");
            ret[i]=time - process[i].getArr_time();
        }
        System.out.println();
    }
}