public abstract class Super {
    protected Process process[]; //프로세스 객체 배열
    protected int pwt[]; //각 프로세스 대기시간
    protected int prt[]; //각 프로세스 응답시간
    protected int ret[]; //각 프로세스 반환시간
    protected int runtime[]; //프로세스의 남은 실행시간
    protected int count; //프로세스 총 개수
    protected int time = 0; //시간
    protected int res_time=1; //응답시간

    public abstract void run(); //
    public abstract void Gantt();
    public Process[] sort(Process process[], int num) {
        if (num == 1) {//1번은 도착시간 순으로 정렬
            Process tp;
            for (int i = 0; i < process.length; i++) {
                int min = process[i].getArr_time();
                int minindex = i;
                for (int j = i; j < process.length; j++) {
                    if (min > process[j].getArr_time()) {
                        min = process[j].getArr_time();
                        minindex = j;
                    }
                }
                if (i == minindex) continue;
                else {
                    tp = process[i];
                    process[i] = process[minindex];
                    process[minindex] = tp;
                }
            }
        } else if (num == 2) { //2. 우선순위 기준으로 정렬
            Process tp;
            for (int i = 0; i < process.length; i++) {
                int min = process[i].getPriority();
                int minindex = i;
                for (int j = i; j < process.length; j++) {
                    if (min > process[j].getPriority()) {
                        min = process[j].getPriority();
                        minindex = j;
                    }
                }
                if (i == minindex) continue;
                else {
                    tp = process[i];
                    process[i] = process[minindex];
                    process[minindex] = tp;
                }
            }
        }
        else if (num == 3) { //3. 서비스 시간 기준으로 정렬
            Process tp;
            for (int i = 0; i < process.length; i++) {
                int min = process[i].getSer_time();
                int minindex = i;
                for (int j = i; j < process.length; j++) {
                    if (min > process[j].getSer_time()) {
                        min = process[j].getSer_time();
                        minindex = j;
                    }
                }
                if (i == minindex) continue;
                else {
                    tp = process[i];
                    process[i] = process[minindex];
                    process[minindex] = tp;
                }
            }
        }
        return process;
    }
    public void Print(){
        double awt = 0;
        for(int i = 0 ; i < count ; i++) {
            System.out.println(process[i].getId() + "의 대기시간 = " + pwt[i]);
            awt+=pwt[i];
        }
        awt/=count;
        System.out.println("평균 대기시간(AWT) = " + awt);

        double art = 0;
        for(int i = 0 ; i < count ; i++) {
            System.out.println(process[i].getId() + "의 응답시간 = " + prt[i]);
            art+=prt[i];
        }
        art/=count;
        System.out.println("평균 응답시간(ART) = " + art);

        double att = 0;
        for(int i = 0 ; i < count ; i++) {
            System.out.println(process[i].getId() + "의 반환시간 = " + ret[i]);
            att+=ret[i];
        }
        att/=count;
        System.out.println("평균 반환시간(ATT) = " + att);
    }
}