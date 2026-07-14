package com.mindvault.goal.entity;

import com.mindvault.common.entity.BaseEntity;
import com.mindvault.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "goals")
public class Goal extends BaseEntity {

    @Column(nullable = false, length = 120)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private int targetValue;

    @Column(nullable = false)
    private int currentValue = 0;

    @Column(nullable =false)
    private boolean completed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
