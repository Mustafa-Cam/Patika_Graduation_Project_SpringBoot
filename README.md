# Patika ve Fmss bilişimin düzenlediği fullstack bootcamp'inin spring boot ile backend tarafı. 
 
# Docker Compose
 swiggy app servisinde resource'deki docker-compose.yml dosyasını çalıştırın (kendi password'lerinizi .env dosyasında belirtebilirsiniz).

# Servisler
## 9 tane servisimiz var.(Notification Servis eksik.)
![image](https://github.com/user-attachments/assets/e8e37139-0bd0-4d58-a711-53997bfff84a)

Service kısmında Spring boot'a gelip run butona tıklayınız. 
![image](https://github.com/user-attachments/assets/f5c69635-513b-46ac-8f5d-be49cbbe82f2)

Bu şekilde servislerimiz çalışacaktır. (Docker'ı çalıştırmayı unutmayın.)

Her şey sorunsuz ise Frontend kısmına geçebiliriz.
https://github.com/Mustafa-Cam/PatikaGraduationProjectNext

Admin oluştururken postman'den 
{
    "username":"Admin2",
    "email":"Mustafa@gmail",
    "password":"deneme123",
    "role":"ADMIN"
}
http://localhost:8080/auth/register
Bu endpoint'e post isteği atıyoruz role kısmı girilmez ise default olarak User olacaktır. Ancak bunun açığı var. Bu şekilde olmaz. Bunun için Eğer Role bilgisi Admin ise ek olarak secret alanı da eklenmeli ve backend'in belirlediği secret girilmelidir. Aklıma bu çözüm geldi. Database'den direkt olarak Role bilgisini de değiştirebiliriz. Aklıma bu iki çözüm geldi.

