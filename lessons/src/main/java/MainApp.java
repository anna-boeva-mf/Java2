public class MainApp {
    public static void main(String[] args) {
        int memberCount = 6, sportCount = 4;
        Object[] members = new Object[memberCount];
        for (int im = 0; im < memberCount; im++) {
            switch ((int) (Math.random() * 3) + 1) {
                case 1:
                    members[im] = new Man("m" + im, (int) (Math.random() * 100), (int) (Math.random() * 100));
                    break;
                case 2:
                    members[im] = new Cat("m" + im, (int) (Math.random() * 50), (int) (Math.random() * 150));
                    break;
                case 3:
                    members[im] = new Robot("m" + im, (int) (Math.random() * 150), (int) (Math.random() * 50));
                    break;
            }
            System.out.println(members[im]);
        }
        System.out.println("-----------");
        Sport[] sports = new Sport[sportCount];
        for (int is = 0; is < sportCount; is++) {
            switch ((int) (Math.random() * 2) + 1) {
                case 1:
                    sports[is] = new RunTrack((int) (Math.random() * 75));
                    break;
                case 2:
                    sports[is] = new Wall((int) (Math.random() * 75));
                    break;
            }
            System.out.println(sports[is]);
        }
        System.out.println("-----------");
        for (int im = 0; im < memberCount; im++) {
            for (int is = 0; is < sportCount; is++) {
                if (((Running) members[im]).run(sports[is]) != 0)
                    break;
                if (((Jumping) members[im]).jump(sports[is]) != 0)
                    break;
            }
            System.out.println("-----------");
        }
    }
}
