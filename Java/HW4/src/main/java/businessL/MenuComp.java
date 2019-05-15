package businessL;

import java.io.Serializable;
import java.util.Comparator;

public class MenuComp implements Comparator<MenuItem>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1507260192555678688L;

	public int compare(MenuItem a, MenuItem b) {
		if (a.getId() < b.getId())
			return -1;
		else if (a.getId() > b.getId())
			return 1;
		else
			return 0;
	}

}
