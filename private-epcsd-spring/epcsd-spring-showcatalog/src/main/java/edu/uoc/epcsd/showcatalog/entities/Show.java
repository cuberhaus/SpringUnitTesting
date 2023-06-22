package edu.uoc.epcsd.showcatalog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Float price;

    @Column(name = "duration")
    private Float duration;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "on_sale_date")
    private java.sql.Timestamp onSaleDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_show")
    private TStatus statusShow;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "show_categories",
            joinColumns = @JoinColumn(name = "id_show"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<Category> categories;

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "performances", joinColumns = @JoinColumn(name="id_show"))
    @JoinColumn(name = "id_show")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Embedded
    private List<Performance> performances;
}
