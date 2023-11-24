let QCount = document.querySelectorAll('.QCount');

console.log(QCount);

for(var i = 0; i < QCount.length; i++){
    QCount[i].textContent = i+1;
}