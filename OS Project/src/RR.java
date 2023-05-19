public class RR extends Super{
    private int timeslice;
    RR(Process process[], int count, int timeslice){
        this.process = process;
        this.count = count;
        pwt = new int[count];
        ret = new int[count];
        prt = new int[count];
        runtime = new int[count];
        for(int i = 0 ; i < count ; i++)pwt[i]=0;
        for(int i = 0 ; i < count ; i++)ret[i]=0;
        for(int i = 0 ; i < count ; i++)prt[i]=0;
        this.timeslice = timeslice;
    }

    public void run(){
        System.out.println("RR 알고리즘 실행");
        process = sort(process, 1);
        for(int i = 0 ; i < count ; i++)runtime[i]=process[i].getSer_time();
        Gantt();
        Print();
    }

    public void Gantt(){
        int queue[] = new int[count];
        int queueoutindex = 0;
        int queueinindex = 0;
        int ncount=0;
        while(isOver(runtime)){
            int isIn = 0;
            if(ncount == 0){
                queue[queueinindex] = 0;
                queueinindex++;
            }
            for(int i = 0 ; i < timeslice && runtime[queue[queueoutindex]] != 0; i++){
                System.out.print(process[queue[queueoutindex]].getId() + " ");
                if(prt[queue[queueoutindex]] == 0)prt[queue[queueoutindex]] = time + res_time - process[queue[queueoutindex]].getArr_time();
                time++;
                runtime[queue[queueoutindex]]--;
                for(int j = 0 ; j < count ; j++){
                    if(process[j].getArr_time() == time){
                        queue[queueinindex] = j;
                        queueinindex++;
                        if(queueinindex == count)queueinindex=0;
                    }
                }
                for(int j = 0 ; j < count ; j++){
                    if(j == queue[queueoutindex])continue;
                    else {
                        if (runtime[j] != 0 && process[j].getArr_time() < time) {
                            pwt[j]++;
                        }
                    }
                }
                isIn++;
            }
            if(isIn>0) {
                System.out.print("| ");
                if(runtime[queue[queueoutindex]] == 0)ret[queue[queueoutindex]]=time-process[queue[queueoutindex]].getArr_time();
            }
            if(runtime[queue[queueoutindex]] != 0){
                queue[queueinindex] = queue[queueoutindex];
                queueinindex++;
            }
            if(queueinindex == count)queueinindex = 0;
            queueoutindex++;
            if(queueoutindex == count)queueoutindex = 0;
            ncount++;
        }
        System.out.println("");
    }
    boolean isOver(int runtime[]){
        for(int i = 0 ; i < count ; i++){
            if(runtime[i] != 0){
                return true;
            }
        }
        return false;
    }
}