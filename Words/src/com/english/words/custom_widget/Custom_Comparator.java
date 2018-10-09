package com.english.words.custom_widget;

import java.text.Collator;
import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class Custom_Comparator implements Comparator {

	@Override
	public int compare(Object lhs, Object rhs) {
		LocalFile l1 = (LocalFile) lhs;
		LocalFile l2 = (LocalFile) rhs;
		Collator collator = Collator.getInstance(java.util.Locale.CHINA);
		if (collator.compare(l1.getName(), l2.getName()) < 0) {
			return -1;
		} else if (collator.compare(l1.getName(), l2.getName()) > 0) {
			return 1;
		} else
			return 0;
	}

}
