/*
 * 
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package ru.tinkoff.fintech.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * If you want, you can generate card model from swagger and use it.
 * Card
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-02-26T16:48:44.711+03:00")
public class Card {
  @JsonProperty("cardNumber")
  private String cardNumber = null;

  @JsonProperty("client")
  private String client = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("loyaltyProgram")
  private String loyaltyProgram = null;

  public Card cardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
    return this;
  }

   /**
   * Get cardNumber
   * @return cardNumber
  **/
  @ApiModelProperty(required = true, value = "")
  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Card client(String client) {
    this.client = client;
    return this;
  }

   /**
   * Get client
   * @return client
  **/
  @ApiModelProperty(required = true, value = "")
  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public Card id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Card loyaltyProgram(String loyaltyProgram) {
    this.loyaltyProgram = loyaltyProgram;
    return this;
  }

   /**
   * Get loyaltyProgram
   * @return loyaltyProgram
  **/
  @ApiModelProperty(required = true, value = "")
  public String getLoyaltyProgram() {
    return loyaltyProgram;
  }

  public void setLoyaltyProgram(String loyaltyProgram) {
    this.loyaltyProgram = loyaltyProgram;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card card = (Card) o;
    return Objects.equals(this.cardNumber, card.cardNumber) &&
        Objects.equals(this.client, card.client) &&
        Objects.equals(this.id, card.id) &&
        Objects.equals(this.loyaltyProgram, card.loyaltyProgram);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardNumber, client, id, loyaltyProgram);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Card {\n");

    sb.append("    cardNumber: ").append(toIndentedString(cardNumber)).append("\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    loyaltyProgram: ").append(toIndentedString(loyaltyProgram)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

