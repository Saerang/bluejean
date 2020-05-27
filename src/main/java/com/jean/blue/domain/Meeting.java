package com.jean.blue.domain;

import com.jean.blue.domain.BaseTimeEntity;
import com.jean.blue.domain.enumeration.MeetingType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Meeting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MeetingType type;

    @Column(nullable = false)
    private int meetingOrder;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String place;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    private boolean isDeleted;

    @Builder
    public Meeting(MeetingType type, Member member, int meetingOrder, String title, String content, String place) {
        this.type = type;
        this.member = member;
        this.meetingOrder = meetingOrder;
        this.title = title;
        this.content = content;
        this.place = place;
    }

    public void update(MeetingType type, int meetingOrder, String title, String content, String place){
        this.type = type;
        this.meetingOrder = meetingOrder;
        this.title = title;
        this.content = content;
        this.place = place;
    }

    public void delete(boolean isDeleted){
        this.isDeleted = isDeleted;
    }

}
