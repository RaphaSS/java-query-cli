package br.com.raphael.javaquerycli.model;

import br.com.raphael.javaquerycli.parsing.annotation.Field;
import br.com.raphael.javaquerycli.parsing.utils.ObjectUtils;

public class Cidade {

	@Field("ibge_id")
	private Long ibgeId;

	@Field("uf")
	private String uf;

	@Field("name")
	private String name;

	@Field("capital")
	private Boolean capital;

	@Field("lon")
	private Double longitute;

	@Field("lat")
	private Double latitude;

	@Field("no_accents")
	private String noAccents;

	@Field("alternative_names")
	private String alternativeNames;

	@Field("microregion")
	private String microregion;

	@Field("mesoregion")
	private String mesoregion;

	public Cidade() {}

	public Cidade(final Long ibgeId, final String uf, final String name, final boolean capital, final Double longitute,
		final Double latitude, final String noAccents, final String alternativeNames, final String microregion,
		final String mesoregion) {
		this.ibgeId = ibgeId;
		this.uf = uf;
		this.name = name;
		this.capital = capital;
		this.longitute = longitute;
		this.latitude = latitude;
		this.noAccents = noAccents;
		this.alternativeNames = alternativeNames;
		this.microregion = microregion;
		this.mesoregion = mesoregion;
	}

	@Override
	public boolean equals(final Object obj) {
		if(obj instanceof Cidade) {
			final Cidade o = (Cidade) obj;
			return ObjectUtils.equals(this, o, Cidade.class);
		}
		return super.equals(obj);
	}

	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(final Long ibgeId) {
		this.ibgeId = ibgeId;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(final String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(final boolean capital) {
		this.capital = capital;
	}

	public Double getLongitute() {
		return longitute;
	}

	public void setLongitute(final Double longitute) {
		this.longitute = longitute;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(final Double latitude) {
		this.latitude = latitude;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(final String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(final String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(final String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(final String mesoregion) {
		this.mesoregion = mesoregion;
	}

}
