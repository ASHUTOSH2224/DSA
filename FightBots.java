import java.util.*;
class FightBots {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine();

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            int X = scanner.nextInt();
            int Y = scanner.nextInt();
            scanner.nextLine();
            String S = scanner.nextLine();

            System.out.println(canBobWin(N, X, Y, S) ? "Yes" : "No");
        }

        scanner.close();
    }

    private static boolean canBobWin(int N, int X, int Y, String S) {

        Map<Character, int[]> directions = new HashMap<>();
        directions.put('L', new int[]{0, -1});
        directions.put('R', new int[]{0, 1});
        directions.put('U', new int[]{1, 0});
        directions.put('D', new int[]{-1, 0});


        Set<String> alicePositions = new HashSet<>();
        int aliceX = 0, aliceY = 0;
        alicePositions.add(aliceX + "," + aliceY);

        for (char move : S.toCharArray()) {
            int[] dir = directions.get(move);
            aliceX += dir[0];
            aliceY += dir[1];
            alicePositions.add(aliceX + "," + aliceY);
        }

        Set<String> bobPositions = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{X, Y, 0});  // {x, y, time}
        bobPositions.add(X + "," + Y);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int time = current[2];

            if (time >= N) {
                continue;
            }

            for (int[] dir : directions.values()) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (alicePositions.contains(nx + "," + ny)) {
                    return true;
                }

                String positionKey = nx + "," + ny;
                if (!bobPositions.contains(positionKey)) {
                    bobPositions.add(positionKey);
                    queue.offer(new int[]{nx, ny, time + 1});
                }
            }
        }

        return false;
    }
}
