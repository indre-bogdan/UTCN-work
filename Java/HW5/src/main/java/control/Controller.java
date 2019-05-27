package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dataL.MonitoredData;

public class Controller {

	private List<MonitoredData> activities;

	public Controller() {
		this.activities = new ArrayList<MonitoredData>();
		System.out.println("1\n");
		read();
		System.out.println("2\n");
		countDays();
		System.out.println("3\n");
		countActivityOverall();
		System.out.println("4\n");
		countActivityDaily();
		System.out.println("5\n");
		duration();
		System.out.println("6\n");
		durationOverall();
		System.out.println("7\n");
		activityPercent();

	}

	private void read() {
		File activity = new File("D:\\All kind of stuff\\me java\\Udemy\\HW5\\Activities.txt");
		try {
			FileInputStream stream = new FileInputStream(activity);
			BufferedReader buff = new BufferedReader(new InputStreamReader(stream));
			String s = null;
				try {
					while((s = buff.readLine()) != null) {
					List<String> splitList = Stream.of(s).map(a -> a.split("\\s+")).flatMap(Arrays::stream)
							.collect(Collectors.toList());
					activities.add(new MonitoredData(splitList.get(0).concat(" " + splitList.get(1)),
							splitList.get(2).concat(" " + splitList.get(3)), splitList.get(4)));

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	private void countDays() {
		long nr = activities.stream().map((a) -> Integer.parseInt((a).getStartTime().substring(8, 10))).distinct()
				.count();
		System.out.println("Number of days: " + nr);
	}

	private Map<String, Long> countActivityOverall() {
		Map<String, Long> m = activities.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		m.forEach((String a, Long b) -> System.out.println(a + " " + b));

		return m;
	}

	private void countActivityDaily() {
	Map<Object, List<MonitoredData>> m = activities.stream().collect(Collectors.groupingBy((a) -> Integer.parseInt((a).getStartTime().substring(8, 10))));
		/*m.forEach((Object a, List<MonitoredData> b) -> {
			System.out.println(a);
			System.out.println();
			b.forEach((MonitoredData c) -> {
				System.out.println(c.getActivity());

			});
		});*/
		m.forEach((Object a, List<MonitoredData> b) -> {
			System.out.println(a);
			Map<String, Long> x = b.stream()
					.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
			x.forEach((String d, Long e) -> {
				System.out.println(d + " " + e);
			});
		});
	//activities.stream().map((a) -> Integer.parseInt((a).getStartTime().substring(8, 10))).collect(Collectors.groupingBy(MonitoredData:: getActivity, Collectors.counting()));
	
	
	}

	private void duration() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		activities.stream().forEach(x -> {
			try {
				System.out.println(
						x.getActivity() + " "
								+ (form.parse(x.getFinishTime()).getTime() - form.parse(x.getStartTime()).getTime())
										/ 1000);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	private void durationOverall() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Long> m = activities.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summingLong(x -> {
					try {
						return (form.parse(x.getFinishTime()).getTime() - form.parse(x.getStartTime()).getTime())
								/ 1000;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return 0;
				})));
		m.forEach((String a, Long b) -> System.out.println(a + " " + b));
	}

	private void activityPercent() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Long> m = activities.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		m.entrySet().stream().map(a -> a.setValue(a.getValue() * 9 / 10));
		// m.forEach((String a, Long b) -> System.out.println(a + " " + b));
		// System.out.println();
		Map<String, Long> n = activities.stream().filter(x -> {
			try {
				return ((form.parse(x.getFinishTime()).getTime() - form.parse(x.getStartTime()).getTime())
						/ 1000) < 300;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		n.forEach((String a, Long b) -> System.out.println(a + " " + b));
		System.out.println();
		m.forEach((String a, Long b) -> {
			if (n.get(a) == null)
				System.out.println(a);
			else if (b > n.get(a))
				System.out.println(a);

		});

	}
}
