package com.english.words.custom_widget;

import java.io.File;

import android.text.TextUtils;

public class testFiles implements LocalFile {
	private File mFile;

	public String mIndex;
	private boolean isDir;

	@Override
	public String getName() {
		return mFile.getName();
	}
	public File getmFile() {
		return mFile;
	}

	public void setmFile(File mFile) {
		this.mFile = mFile;
	}


	@Override
	public String getPath() {
		return mFile.getPath();
	}

	@Override
	public String getIndex() {
//		if (!TextUtils.isEmpty(mIndex)) {
			return mIndex;
//		}
//		return StringHelper.getHeadChar(getName());
	}

	@Override
	public void delMySelf() {
//		if (mFile == null || !mFile.exists()) {
//			return;
//		}
//		if (mFile.isDirectory()) {
//			File files[] = mFile.listFiles();
//			for (int i = 0; i < files.length; i++) {
//				files[i].delete();
//			}
//		}
//		mFile.delete();
	}

}