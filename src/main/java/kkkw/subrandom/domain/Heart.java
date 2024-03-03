package kkkw.subrandom.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    @NotNull
    private Review review;

    @Builder
    public Heart(Long id, Member member, Review review) {
        this.id = id;
        this.member = member;
        this.review = review;
    }

    //==생성 메서드 + 리뷰 좋아요 수 로직==//
    public static Heart createHeart(Member member, Review review) {
        Heart heart = Heart.builder()
                .member(member)
                .review(review)
                .build();
        review.countHearts();
        return heart;
    }
}