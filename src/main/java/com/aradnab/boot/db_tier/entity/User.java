package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class User {
    private int id;
    private int titleId;
    private int genderId;
    private int imageId;
    private int countryId;
    private String userId;
    private String fName;
    private String lName;
    private String sName;
    private String fullName;
    private Byte activeState;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<Account> accountsById;
    private Collection<Address> addressesById;
    private Collection<AuthenticatedUser> authenticatedUsersById;
    private Collection<BankCard> bankCardsById;
    private Collection<Cart> cartsById;
    private Collection<ContactNumber> contactNumbersById;
    private Collection<Email> emailsById;
    private Collection<LoginCredentialFb> loginCredentialFbsById;
    private Collection<LoginCredentialGoogle> loginCredentialGooglesById;
    private Collection<LoginCredentialNormal> loginCredentialNormalsById;
    private Collection<Message> messagesById;
    private Collection<Message> messagesById_0;
    private Collection<Note> notesById;
    private Collection<Order> ordersById;
    private Collection<ProductRating> productRatingsById;
    private Collection<SavedProduct> savedProductsById;
    private Collection<SavedSearch> savedSearchesById;
    private Collection<SavedShop> savedShopsById;
    private Collection<SavedUser> savedUsersById;
    private Collection<SavedUser> savedUsersById_0;
    private Collection<SearchHistory> searchHistoriesById;
    private Collection<Shop> shopsById;
    private Collection<ShopRating> shopRatingsById;
    private Collection<Transaction> transactionsById;
    private Title titleByTitleId;
    private Gender genderByGenderId;
    private Image imageByImageId;
    private Country countryByCountryId;
    private Collection<UserNotification> userNotificationsById;
    private Collection<ViewedProduct> viewedProductsById;
    private Collection<Wishlist> wishlistsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title_id", nullable = false)
    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    @Basic
    @Column(name = "gender_id", nullable = false)
    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    @Basic
    @Column(name = "image_id", nullable = false)
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "country_id", nullable = false)
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 25)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "f_name", nullable = true, length = 60)
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    @Basic
    @Column(name = "l_name", nullable = true, length = 60)
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Basic
    @Column(name = "s_name", nullable = true, length = 60)
    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Basic
    @Column(name = "full_name", nullable = true, length = 250)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "active_state", nullable = true)
    public Byte getActiveState() {
        return activeState;
    }

    public void setActiveState(Byte activeState) {
        this.activeState = activeState;
    }

    @Basic
    @Column(name = "saved_at", nullable = true)
    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    @Basic
    @Column(name = "last_updated_at", nullable = true)
    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Basic
    @Column(name = "deleted_at", nullable = true)
    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                titleId == user.titleId &&
                genderId == user.genderId &&
                imageId == user.imageId &&
                countryId == user.countryId &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(fName, user.fName) &&
                Objects.equals(lName, user.lName) &&
                Objects.equals(sName, user.sName) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(activeState, user.activeState) &&
                Objects.equals(savedAt, user.savedAt) &&
                Objects.equals(lastUpdatedAt, user.lastUpdatedAt) &&
                Objects.equals(deletedAt, user.deletedAt) &&
                Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleId, genderId, imageId, countryId, userId, fName, lName, sName, fullName, activeState, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Account> getAccountsById() {
        return accountsById;
    }

    public void setAccountsById(Collection<Account> accountsById) {
        this.accountsById = accountsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Address> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<Address> addressesById) {
        this.addressesById = addressesById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<AuthenticatedUser> getAuthenticatedUsersById() {
        return authenticatedUsersById;
    }

    public void setAuthenticatedUsersById(Collection<AuthenticatedUser> authenticatedUsersById) {
        this.authenticatedUsersById = authenticatedUsersById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<BankCard> getBankCardsById() {
        return bankCardsById;
    }

    public void setBankCardsById(Collection<BankCard> bankCardsById) {
        this.bankCardsById = bankCardsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Cart> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<Cart> cartsById) {
        this.cartsById = cartsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ContactNumber> getContactNumbersById() {
        return contactNumbersById;
    }

    public void setContactNumbersById(Collection<ContactNumber> contactNumbersById) {
        this.contactNumbersById = contactNumbersById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Email> getEmailsById() {
        return emailsById;
    }

    public void setEmailsById(Collection<Email> emailsById) {
        this.emailsById = emailsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<LoginCredentialFb> getLoginCredentialFbsById() {
        return loginCredentialFbsById;
    }

    public void setLoginCredentialFbsById(Collection<LoginCredentialFb> loginCredentialFbsById) {
        this.loginCredentialFbsById = loginCredentialFbsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<LoginCredentialGoogle> getLoginCredentialGooglesById() {
        return loginCredentialGooglesById;
    }

    public void setLoginCredentialGooglesById(Collection<LoginCredentialGoogle> loginCredentialGooglesById) {
        this.loginCredentialGooglesById = loginCredentialGooglesById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<LoginCredentialNormal> getLoginCredentialNormalsById() {
        return loginCredentialNormalsById;
    }

    public void setLoginCredentialNormalsById(Collection<LoginCredentialNormal> loginCredentialNormalsById) {
        this.loginCredentialNormalsById = loginCredentialNormalsById;
    }

    @OneToMany(mappedBy = "userByUserIdFrom")
    public Collection<Message> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<Message> messagesById) {
        this.messagesById = messagesById;
    }

    @OneToMany(mappedBy = "userByUserIdTo")
    public Collection<Message> getMessagesById_0() {
        return messagesById_0;
    }

    public void setMessagesById_0(Collection<Message> messagesById_0) {
        this.messagesById_0 = messagesById_0;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Note> getNotesById() {
        return notesById;
    }

    public void setNotesById(Collection<Note> notesById) {
        this.notesById = notesById;
    }

    @OneToMany(mappedBy = "userByOrderBy")
    public Collection<Order> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Order> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ProductRating> getProductRatingsById() {
        return productRatingsById;
    }

    public void setProductRatingsById(Collection<ProductRating> productRatingsById) {
        this.productRatingsById = productRatingsById;
    }

    @OneToMany(mappedBy = "userBySavedBy")
    public Collection<SavedProduct> getSavedProductsById() {
        return savedProductsById;
    }

    public void setSavedProductsById(Collection<SavedProduct> savedProductsById) {
        this.savedProductsById = savedProductsById;
    }

    @OneToMany(mappedBy = "userBySavedBy")
    public Collection<SavedSearch> getSavedSearchesById() {
        return savedSearchesById;
    }

    public void setSavedSearchesById(Collection<SavedSearch> savedSearchesById) {
        this.savedSearchesById = savedSearchesById;
    }

    @OneToMany(mappedBy = "userBySavedBy")
    public Collection<SavedShop> getSavedShopsById() {
        return savedShopsById;
    }

    public void setSavedShopsById(Collection<SavedShop> savedShopsById) {
        this.savedShopsById = savedShopsById;
    }

    @OneToMany(mappedBy = "userBySavedBy")
    public Collection<SavedUser> getSavedUsersById() {
        return savedUsersById;
    }

    public void setSavedUsersById(Collection<SavedUser> savedUsersById) {
        this.savedUsersById = savedUsersById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<SavedUser> getSavedUsersById_0() {
        return savedUsersById_0;
    }

    public void setSavedUsersById_0(Collection<SavedUser> savedUsersById_0) {
        this.savedUsersById_0 = savedUsersById_0;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<SearchHistory> getSearchHistoriesById() {
        return searchHistoriesById;
    }

    public void setSearchHistoriesById(Collection<SearchHistory> searchHistoriesById) {
        this.searchHistoriesById = searchHistoriesById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Shop> getShopsById() {
        return shopsById;
    }

    public void setShopsById(Collection<Shop> shopsById) {
        this.shopsById = shopsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ShopRating> getShopRatingsById() {
        return shopRatingsById;
    }

    public void setShopRatingsById(Collection<ShopRating> shopRatingsById) {
        this.shopRatingsById = shopRatingsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Transaction> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<Transaction> transactionsById) {
        this.transactionsById = transactionsById;
    }

    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Title getTitleByTitleId() {
        return titleByTitleId;
    }

    public void setTitleByTitleId(Title titleByTitleId) {
        this.titleByTitleId = titleByTitleId;
    }

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Gender getGenderByGenderId() {
        return genderByGenderId;
    }

    public void setGenderByGenderId(Gender genderByGenderId) {
        this.genderByGenderId = genderByGenderId;
    }

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Image getImageByImageId() {
        return imageByImageId;
    }

    public void setImageByImageId(Image imageByImageId) {
        this.imageByImageId = imageByImageId;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Country getCountryByCountryId() {
        return countryByCountryId;
    }

    public void setCountryByCountryId(Country countryByCountryId) {
        this.countryByCountryId = countryByCountryId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<UserNotification> getUserNotificationsById() {
        return userNotificationsById;
    }

    public void setUserNotificationsById(Collection<UserNotification> userNotificationsById) {
        this.userNotificationsById = userNotificationsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ViewedProduct> getViewedProductsById() {
        return viewedProductsById;
    }

    public void setViewedProductsById(Collection<ViewedProduct> viewedProductsById) {
        this.viewedProductsById = viewedProductsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Wishlist> getWishlistsById() {
        return wishlistsById;
    }

    public void setWishlistsById(Collection<Wishlist> wishlistsById) {
        this.wishlistsById = wishlistsById;
    }
}
