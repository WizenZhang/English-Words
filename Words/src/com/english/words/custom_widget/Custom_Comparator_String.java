package com.english.words.custom_widget;

import java.text.Collator;
import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class Custom_Comparator_String implements Comparator {

	@Override
	public int compare(Object lhs, Object rhs) {
		String str1 = (String) lhs;
		String str2 = (String) rhs;
		Collator collator = Collator.getInstance(java.util.Locale.CHINA);
		if (collator.compare(str1, str2) < 0) {
			return -1;
		} else if (collator.compare(str1, str2) > 0) {
			return 1;
		} else
			return 0;
	}

}
