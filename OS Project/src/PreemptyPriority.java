public class PreemptyPriority extends Super{
    PreemptyPriority(Process process[], int count){
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
        System.out.println("선점형 Priority 알고리즘 실행");
        process = sort(process, 1);
        process = sort(process,2);
        for(int i = 0 ; i < count ; i++)runtime[i]=process[i].getSer_time();
        Gantt();
        Print();
    }
    public void Gantt(){
        int pindex = 0;
        int minarr = process[0].getArr_time();
        for(int i = 0 ; i < count ; i++){
            if(minarr>process[i].getArr_time()) {
                minarr = process[i].getArr_time();
                pindex = i;
            }
        }
        int j = 0;
        while(isOver(runtime)){
            int isIn=0;
            for(int i = 0 ; i < runtime[pindex];){
                System.out.print(process[pindex].getId() + " ");
                if(prt[pindex]==0)prt[pindex]=time+res_time-process[pindex].getArr_time();
                time++;
                for(int x = 0 ; x < count ; x++){
                    if(x == pindex) continue;
                    else{
                        if(process[x].getArr_time()<time && runtime[x]!=0)
                            pwt[x]++;
                    }
                }
                runtime[pindex]--;
                for(j = 0 ; j < pindex ; j++){
                    if(process[j].getArr_time() == time && runtime[j] != 0){
                        i = runtime[pindex];
                        break;
                    }
                }
                isIn++;
            }
            if(isIn>0)System.out.print("| ");
            if(runtime[pindex] == 0)ret[pindex] = time-process[pindex].getArr_time();
            if(pindex == j) {
                pindex++;
                j++;
            }
            else pindex = j;
        }
        System.out.println();
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