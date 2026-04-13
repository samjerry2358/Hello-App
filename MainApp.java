import java.util.*;

// BASE CLASS
class Athlete {
    String name;
    int age;
    String sport;

    public Athlete(String name, int age, String sport) {
        this.name = name;
        this.age = age;
        this.sport = sport;
    }
}

// 1. TeamRoster
class TeamRoster {
    private List<Athlete> athletes = new ArrayList<>();

    public void addAthlete(Athlete a) {
        athletes.add(a);
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }
}

// 2. AthleteViewer
class AthleteViewer {
    private List<Athlete> athletes;

    public AthleteViewer(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public void displayAll() {
        for (Athlete a : athletes) {
            System.out.println(a.name + " - " + a.sport);
        }
    }

    public void displayBySport(String sport) {
        boolean found = false;
        for (Athlete a : athletes) {
            if (a.sport.equalsIgnoreCase(sport)) {
                System.out.println(a.name + " - " + a.sport);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Invalid sport");
        }
    }
}

// 3. PerformanceTracker
class PerformanceTracker {
    private int[][] scores;

    public PerformanceTracker(int[][] scores) {
        this.scores = scores;
    }

    public int getTotalScore(int index) {
        int total = 0;
        for (int s : scores[index]) {
            if (s < 0) throw new IllegalArgumentException("Score cannot be negative");
            total += s;
        }
        return total;
    }

    public double getAverageScore(int index) {
        int total = getTotalScore(index);
        return (double) total / scores[index].length;
    }

    public int[] getScores(int index) {
        return scores[index];
    }
}

// 4. QualificationCheck
class QualificationCheck {
    public boolean isQualified(int[] scores, int cutoff, int age) {
        if (age < 18) return false;

        int total = 0;
        for (int s : scores) {
            if (s < cutoff) return false;
            total += s;
        }

        double avg = (double) total / scores.length;
        return avg >= cutoff;
    }
}

// 5. SportDesk
class SportDesk {
    private Map<String, Integer> sports = new HashMap<>();

    public void addSport(String sport, int maxParticipants) {
        sports.put(sport.toLowerCase(), maxParticipants);
    }

    public boolean checkAvailability(String sport) {
        return sports.containsKey(sport.toLowerCase()) && sports.get(sport.toLowerCase()) > 0;
    }
}

// 7. Interface
interface Rewardable {
    void giveReward();
}

// 6. EventReport + Reward Implementation
class EventReport implements Rewardable {
    private boolean qualified;

    public EventReport(boolean qualified) {
        this.qualified = qualified;
    }

    public void printReport(Athlete a, int total, double avg, boolean qualified) {
        System.out.println("Athlete: " + a.name);
        System.out.println("Sport: " + a.sport);
        System.out.println("Total Score: " + total);
        System.out.println("Average Score: " + avg);
        System.out.println("Qualified: " + (qualified ? "YES" : "NO"));
    }

    public void giveReward() {
        if (qualified) {
            System.out.println("Reward: Congratulations!\n");
        }
    }
}

// MAIN CLASS
public class MainApp {
    public static void main(String[] args) {

        // Create Athletes
        Athlete a1 = new Athlete("Sam", 22, "Swimming");
        Athlete a2 = new Athlete("Leo", 25, "Athletics");
        Athlete a3 = new Athlete("Nina", 19, "Swimming");

        // Team Roster
        TeamRoster roster = new TeamRoster();
        roster.addAthlete(a1);
        roster.addAthlete(a2);
        roster.addAthlete(a3);

        // Scores
        int[][] scores = {
            {88, 76, 91},
            {70, 60, 65},
            {95, 90, 93}
        };

        PerformanceTracker tracker = new PerformanceTracker(scores);
        QualificationCheck qc = new QualificationCheck();

        // Sports
        SportDesk desk = new SportDesk();
        desk.addSport("Swimming", 2);
        desk.addSport("Athletics", 1);

        int cutoff = 75;

        List<Athlete> athletes = roster.getAthletes();

        for (int i = 0; i < athletes.size(); i++) {
            Athlete a = athletes.get(i);

            if (!desk.checkAvailability(a.sport)) {
                System.out.println("Invalid sport");
                continue;
            }

            int total = tracker.getTotalScore(i);
            double avg = tracker.getAverageScore(i);
            boolean qualified = qc.isQualified(tracker.getScores(i), cutoff, a.age);

            EventReport report = new EventReport(qualified);
            report.printReport(a, total, avg, qualified);
            report.giveReward();
        }
    }
}
