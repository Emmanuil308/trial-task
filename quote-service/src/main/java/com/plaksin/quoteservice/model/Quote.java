package com.plaksin.quoteservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(generator = "quote_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "quote_gen", sequenceName = "quote_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "creat_or_update_time")
    private LocalDateTime creatOrUpdateTime;

    /**
     * Entity in auth-service
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quote", cascade = CascadeType.REMOVE)
    private Set<Vote> votes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(id, quote.id) && Objects.equals(content, quote.content) && Objects.equals(creatOrUpdateTime, quote.creatOrUpdateTime) && userId.equals(quote.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, creatOrUpdateTime, userId);
    }
}
