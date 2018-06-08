package ru.podstavkov.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Ad;

@Repository("adDAO")
@Transactional
public class AdDAO extends AbstractDAO{
	
	public List<Ad> getListAd() {

		return em.createQuery("SELECT e FROM Ad e", Ad.class).getResultList();
	}

	public Ad merge(Ad ad) {
		return em.merge(ad);
	}
	
	public void persist(Ad ad) {
		if (ad == null)
			return;
		em.persist(ad);
	}

	public Ad getAdById(String id) {
		if (id == null)
			return null;
		return em.find(Ad.class, id);
	}

	public void removeAd(Ad ad) {
		if (ad == null)
			return;
		removeAd(ad.getId());
	}

	public void removeAd(String adid) {
		if (adid == null || adid.isEmpty())
			return;
		Ad ad = em.find(Ad.class, adid);
		em.remove(ad);

	}
}
