package com.zhibo;

public enum ViewSelect {
	link{
		public ViewSelect next() { return tree;}
	},
	tree{
		public ViewSelect next() { return link;}
	};
	abstract public ViewSelect next();
}
