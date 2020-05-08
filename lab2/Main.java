import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Loader[] loaders = new Loader[270];
		for (int i = 0; i < 270; i++) {
			loaders[i] = new Loader(i + 1);
		}
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Cruise[] cruises = new Cruise[n];
		for (int i = 0; i < n; i++) {
			String id = sc.next();
			if (id.startsWith("S")) {
				int time = sc.nextInt();
				cruises[i] = new SmallCruise(id, time);
			} else {
				 int time = sc.nextInt();
				 double length = sc.nextDouble();
				 double passengers = sc.nextDouble();
				 cruises[i] = new BigCruise(id, time, length, passengers);
			}
		}
		for (Cruise c : cruises) {
			if (c instanceof SmallCruise) {
				int i = 0;
				boolean run = true;
				while (run) {
					if (loaders[i].canServe(c)) {
						loaders[i] = loaders[i].serve(c);
						System.out.println(loaders[i]);
						run = false;
					} else {
						i++;
					}
				}
			} else {
				int loadersRequired = c.getNumOfLoadersRequired();
				int i = 0;
				for (int j = 0; j < loadersRequired; j++) {
					boolean run = true;
					while (run) {
						if (loaders[i].canServe(c)) {
							loaders[i] = loaders[i].serve(c);
							System.out.println(loaders[i]);
							run = false;
						} else {
							i++;
						}
					}
				}
			}
		}
		sc.close();
	}
}
