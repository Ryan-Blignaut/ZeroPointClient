package me.thesilverecho.zeropoint.gui.potionHud;

import me.thesilverecho.zeropoint.config.Settings;
import net.minecraft.entity.effect.StatusEffect;

public class EffectSetting
{
	private StatusEffect statusEffect;
	private Integer timerColor, nameColor, effectId, flickerStartTime, flickerSpeed;
	private Boolean chromaTimer, chromaName, enabled, bold, italic, underlined, flickerWhenLow;
	private String customName;

	public EffectSetting(/*Integer effectId*/StatusEffect value)
	{
		this.statusEffect = value;
		this.timerColor = -1;
		this.nameColor = -1;
		this.enabled = true;
		this.flickerWhenLow = true;
		this.flickerSpeed = 16;
		this.customName = "";
	}

	public EffectSetting(Integer effectId)
	{
		this.effectId = effectId;
		this.timerColor = -1;
		this.nameColor = -1;
		this.enabled = true;
		this.flickerWhenLow = true;
		this.flickerSpeed = 16;
		this.customName = "";
	}


	public EffectSetting sync(EffectSetting effectSetting)
	{
		EffectSetting base = Settings.EFFECT_SETTINGS.get(effectId);
		EffectSetting endResult = Settings.EFFECT_SETTINGS.get(effectId);
		endResult.setTimerColor(base.getTimerColor())
				.setNameColor(base.getNameColor())
				.setChromaName(base.getChromaName())
				.setChromaTimer(base.getChromaTimer())
				.setEnabled(base.isEnabled())
				.setBold(base.getBold())
				.setItalic(base.getItalic())
				.setUnderlined(base.getUnderlined())
				.setFlickerWhenLow(base.getFlickerWhenLow())
				.setFlickerStartTime(base.getFlickerStartTime())
				.setFlickerSpeed(base.getFlickerSpeed())
				.setCustomName(base.getCustomName());
		return endResult;
	}

	public EffectSetting setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
		return this;
	}

	public EffectSetting setCustomName(String customName)
	{
		this.customName = customName;
		return this;
	}

	public EffectSetting setNameColor(Integer nameColor)
	{
		this.nameColor = nameColor;
		return this;
	}

	public Integer getTimerColor()
	{
		return timerColor;
	}

	public EffectSetting setTimerColor(Integer timerColor)
	{
		this.timerColor = timerColor;
		return this;
	}

	public Integer getNameColor()
	{
		return nameColor;
	}

	public Integer getEffectId()
	{
		return effectId;
	}

	public EffectSetting setEffectId(Integer effectId)
	{
		this.effectId = effectId;
		return this;
	}

	public Integer getFlickerStartTime()
	{
		return flickerStartTime;
	}

	public EffectSetting setFlickerStartTime(Integer flickerStartTime)
	{
		this.flickerStartTime = flickerStartTime;
		return this;
	}

	public Integer getFlickerSpeed()
	{
		return flickerSpeed;
	}

	public EffectSetting setFlickerSpeed(Integer flickerSpeed)
	{
		this.flickerSpeed = flickerSpeed;
		return this;
	}

	public Boolean getChromaTimer()
	{
		return chromaTimer;
	}

	public EffectSetting setChromaTimer(Boolean chromaTimer)
	{
		this.chromaTimer = chromaTimer;
		return this;
	}

	public Boolean getChromaName()
	{
		return chromaName;
	}

	public EffectSetting setChromaName(Boolean chromaName)
	{
		this.chromaName = chromaName;
		return this;
	}

	public Boolean isEnabled()
	{
		return enabled;
	}

	public Boolean getBold()
	{
		return bold;
	}

	public EffectSetting setBold(Boolean bold)
	{
		this.bold = bold;
		return this;
	}

	public Boolean getItalic()
	{
		return italic;
	}

	public EffectSetting setItalic(Boolean italic)
	{
		this.italic = italic;
		return this;
	}

	public Boolean getUnderlined()
	{
		return underlined;
	}

	public EffectSetting setUnderlined(Boolean underlined)
	{
		this.underlined = underlined;
		return this;
	}

	public Boolean getFlickerWhenLow()
	{
		return flickerWhenLow;
	}

	public EffectSetting setFlickerWhenLow(Boolean flickerWhenLow)
	{
		this.flickerWhenLow = flickerWhenLow;
		return this;
	}

	public String getCustomName()
	{
		return customName;
	}
}
