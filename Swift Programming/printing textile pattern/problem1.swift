
//taking input

let length = Int(readLine()!)
let breadth = Int(readLine()!)

var gaps = [Int]()

for _ in 1...length!{
    if let input = Int(readLine()!){
        gaps.append(input)
    }
}


//printing pattern using for loop
for i in 0..<length! {
    
    print("x", terminator:"")
    var temp=0
    
    for _ in 2...breadth! {
    
        if(temp == gaps[i]){
            print("x", terminator :"")
            temp = 0
        }else{
            print("o", terminator : "")
            temp += 1
        }
        
    }
    print("")
    
}






