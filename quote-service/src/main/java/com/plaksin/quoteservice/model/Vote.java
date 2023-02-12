package com.plaksin.quoteservice.model;

import com.plaksin.quoteservice.enums.VoteValues;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vote")
@Getter
@Setter
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(generator = "vote_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "vote_gen", sequenceName = "vote_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "vote_value", nullable = false)
    @Enumerated(EnumType.STRING)
    private VoteValues value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;

    /**
     * Entity in auth-service
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(id, vote.id) && value == vote.value && quote.equals(vote.quote) && userId.equals(vote.userId) && creationTime.equals(vote.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, quote, userId, creationTime);
    }
}
