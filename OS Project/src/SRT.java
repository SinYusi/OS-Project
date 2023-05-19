public class SRT extends Super{
    int timeslice;
    SRT(Process process[], int count, int timeslice){
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
        System.out.println("SRT 알고리즘 실행");
        process = sort(process, 1);
        for(int i = 0 ; i < count ; i++)runtime[i]=process[i].getSer_time();
        Gantt();
        Print();
    }

    public void Gantt(){
        int pindex=0;
        int minarr = process[0].getArr_time();
        for(int i = 0 ; i < count ; i++){
            if(minarr>process[i].getArr_time()) {
                minarr = process[i].getArr_time();
                pindex = i;
            }
        }
        while(isOver(runtime)){
            int isIn=0;
            for(int j = 0; j < timeslice && runtime[pindex]!=0 ; j++){
                if(prt[pindex]==0)prt[pindex]=time+res_time-process[pindex].getArr_time();
                runtime[pindex]--;
                System.out.print(process[pindex].getId() + " ");
                time++;
                for(int x = 0 ; x < count ; x++){
                    if(pindex == x)continue;
                    else{
                        if(process[x].getArr_time()<time && runtime[x]!=0)
                            pwt[x]++;
                    }
                }
                isIn++;
            }
            if(isIn>0) {
                System.out.print("| ");
                if(runtime[pindex] == 0)ret[pindex]=time-process[pindex].getArr_time();
            }
            int oppindex = pindex;
            if(runtime[pindex] == 0) {
                oppindex = pindex;
                runtime[pindex] = 10000;
            }
            for(int i = 0 ; i < count ; i++){
                if(runtime[i]!= 0 && process[i].getArr_time()<=time && runtime[pindex]>runtime[i])
                    pindex = i;
            }
            if(runtime[oppindex] == 10000) runtime[oppindex] = 0;
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