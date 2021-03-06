package com.github.drepic26.couponcodes.core.coupon;

import java.util.HashMap;

import com.github.drepic26.couponcodes.core.ServerModTransformer;

public class Coupon {

	private String name;
	private int usetimes;
	private int time;
	private boolean expired;
	private HashMap<String, Boolean> usedplayers;

	public Coupon(String name, int usetimes, int time, HashMap<String, Boolean> usedplayers) {
		this.name = name;
		this.usetimes = usetimes;
		this.time = time;
		this.usedplayers = usedplayers;
		this.expired = (usetimes <= 0 || time == 0);
	}

	public boolean addToDatabase() {
		return ServerModTransformer.getInstance().getCouponHandler().addCouponToDatabase(this);
	}

	public boolean removeFromDatabase() {
		return ServerModTransformer.getInstance().getCouponHandler().removeCouponFromDatabase(this);
	}

	public boolean isInDatabase() {
		return ServerModTransformer.getInstance().getCouponHandler().couponExists(this);
	}

	public void updateWithDatabase() {
		ServerModTransformer.getInstance().getCouponHandler().updateCoupon(this);
	}

	public void updateTimeWithDatabase() {
		ServerModTransformer.getInstance().getCouponHandler().updateCouponTime(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUseTimes() {
		return usetimes;
	}

	public void setUseTimes(int usetimes) {
		this.usetimes = usetimes;
		if (this.usetimes <= 0)
			this.setExpired(true);
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
		if (this.time == 0)
			this.setExpired(true);
	}

	public HashMap<String, Boolean> getUsedPlayers() {
		return usedplayers;
	}

	public void setUsedPlayers(HashMap<String, Boolean> usedplayers) {
		this.usedplayers = usedplayers;
	}

	public String getType() {
		if (this instanceof ItemCoupon) return "Item";
		if (this instanceof EconomyCoupon) return "Economy";
		if (this instanceof RankCoupon) return "Rank";
		if (this instanceof XpCoupon) return "Xp";
		else
			return null;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}
}
