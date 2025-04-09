package com.example.codehive.service;

import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.entity.FavoriteMarketId;
import com.example.codehive.repository.FavoriteMarketRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteCoinMarketServiceImp implements FavoriteCoinMarketService {
    private final FavoriteMarketRepository favoriteMarketRepository;
    private final EntityManager entityManager;

    @Override
    public List<String> readByUserNo(int userNo) {
        List<FavoriteMarket> list = favoriteMarketRepository.findByUserNo(userNo);
        List<String> markets = list.stream()
                .map(FavoriteMarket::getMarket).toList();
        return markets;
    }

    @Override
    @Transactional
    public void regiseter(FavoriteMarket favoriteMarket) {
        FavoriteMarketId favoriteMarketId = new FavoriteMarketId();
        favoriteMarketId.setUserNo(favoriteMarket.getUserNo());
        favoriteMarketId.setMarket(favoriteMarket.getMarket());
        FavoriteMarket favList = entityManager.find(FavoriteMarket.class, favoriteMarketId);
        if (favList != null) {
            throw new IllegalArgumentException("이미 관심목록에 추가되어 있습니다.");
        }
        entityManager.persist(favoriteMarket);
    }

    @Override
    @Transactional
    public void remove(FavoriteMarketId favoriteMarketid) {
        FavoriteMarket removeFavMarket = entityManager.find(FavoriteMarket.class, favoriteMarketid);
        if (removeFavMarket == null) {
            throw new IllegalArgumentException("이미 관심목록에서 제거되었스니다.");
        }
        entityManager.remove(removeFavMarket);
    }
}
