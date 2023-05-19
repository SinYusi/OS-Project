public class NpreemptyPriority extends Super{
    NpreemptyPriority(Process process[], int count){
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
        System.out.println("비선점 Priority 알고리즘 실행");
        process = sort(process,1);
        process = sort(process,2);
        int min = process[0].getArr_time();
        int minindex = 0;
        for(int i = 0 ; i < count ; i++){
            if(min>process[i].getArr_time()){
                min = process[i].getArr_time();
                minindex = i;
            }
        }
        Process tp = process[minindex];
        for(int i = minindex ; i > 0 ; i --){
            process[i] = process[i-1];
        }
        process[0]=tp;
        for(int i = 0 ; i < count ; i++)runtime[i]=process[i].getSer_time();
        Gantt();
        Print();
    }

    public void Gantt(){
        for(int i = 0 ; i < count ; i++){
            if(process[i].getArr_time()>time){
                int j;
                for(j = i ; j < count;j++){
                    if(process[j].getArr_time()<time)
                        break;
                }
                Process tp = process[j];
                for (int x = j; x > i; x--) {
                    process[x] = process[x - 1];
                }
                process[i] = tp;
            }
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