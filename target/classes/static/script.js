const fetchContract = async () => {
    const res = await fetch('/SimpleContract.json');
    const data = await res.json();
    return data;
}



async function blockData() {
    try {
        const d = await fetchContract(); // Consider using 'await' here
        if (window.ethereum) {
            window.web3 = new Web3(window.ethereum);
            await window.ethereum.enable();
        } else if (window.web3) {
            window.web3 = new Web3(window.web3.currentProvider);
        } else {
            window.alert('Non-Ethereum browser detected. You should consider trying MetaMask!');
            return null;
        }

        const web3 = window.web3;
        const networkId = await web3.eth.net.getId();
        const contractAddress = d.networks[networkId].address;
        const contractABI = d.abi;

        async function initialiseContract() {
            const accounts = await web3.eth.getAccounts();
            const contract = new web3.eth.Contract(contractABI, contractAddress);

            // Store in sessionStorage
            sessionStorage.setItem('myAddress', JSON.stringify(accounts[0]));
            sessionStorage.setItem('contractABI', JSON.stringify(contractABI));
            sessionStorage.setItem('contractAddress', JSON.stringify(contractAddress));

            return contract;
        }

     

        const contract = await initialiseContract();
        
        
    //     return contract;
        return {
         contract,
         initialiseContract,
         getTransactionCount
        }

    } catch (err) {
        console.error(err);
        return null;
    }
}

async function login() {
    try {
        const cont = (await blockData());
        
        if (!cont) {
            return "error";
        } else {
            return "success";
        }
    } catch (err) {
        console.error(err);
        return "error";
    }
}

async function paySeller(index,p){
    const contractAddress = JSON.parse(sessionStorage.getItem('contractAddress'))
    const contractABI = JSON.parse(sessionStorage.getItem('contractABI'))
    const myAddress= JSON.parse(sessionStorage.getItem('myAddress'))
    

    if (window.ethereum) {
        window.web3 = new Web3(window.ethereum);
        await window.ethereum.enable();
    } else if (window.web3) {
        window.web3 = new Web3(window.web3.currentProvider);
    } else {
        window.alert('Non-Ethereum browser detected. You should consider trying MetaMask!');
        return null;
    }
    const web3 = window.web3;
    
    const contract = new web3.eth.Contract(contractABI, contractAddress);
    const myacc = await web3.eth.getAccounts();
    const userAddress = myacc[0]    
    //  
    
//todo
    await contract.methods.PaySeller(index).send({
        from:userAddress,
        gas: 3000000,
        value:p
    })
    .on('transactionHash', function(hash){
        console.log('Transaction hash:', hash);
      })
      .on('confirmation', function(confirmationNumber, r){
        console.log('Confirmation number:', confirmationNumber);
        if (confirmationNumber === 1) {
          // Transaction has been confirmed
            console.log('Transaction receipt:', r);
       }
   })
   .on('error', function(error){
        console.error('Error occurred:', error);
      
      });
    

}

async function cancelSeller(index){
    const contractAddress = JSON.parse(sessionStorage.getItem('contractAddress'))
    const contractABI = JSON.parse(sessionStorage.getItem('contractABI'))
    const myAddress= JSON.parse(sessionStorage.getItem('myAddress'))
    

    if (window.ethereum) {
        window.web3 = new Web3(window.ethereum);
        await window.ethereum.enable();
    } else if (window.web3) {
        window.web3 = new Web3(window.web3.currentProvider);
    } else {
        window.alert('Non-Ethereum browser detected. You should consider trying MetaMask!');
        return null;
    }
    const web3 = window.web3;
    
    const contract = new web3.eth.Contract(contractABI, contractAddress);
    const myacc = await web3.eth.getAccounts();
    const userAddress = myacc[0]    
    //  
    
//todo
    await contract.methods.cancelTransaction(index).send({
        from:userAddress
       })
       .on('transactionHash', function(hash){
           // console.log('Transaction hash:', hash);
       })
       .on('confirmation', function(confirmationNumber, r){
           console.log('Confirmation number:', confirmationNumber);
           if (confirmationNumber === 1) {
           // Transaction has been confirmed
               console.log('Transaction receipt:', r);
           }
       })
       .on('error', function(error){
        console.error('Error occurred:', error);
      
      });
    

}


async function getTransactionCount() {
    const contractAddress = JSON.parse(sessionStorage.getItem('contractAddress'))
    const contractABI = JSON.parse(sessionStorage.getItem('contractABI'))
    const myAddress= JSON.parse(sessionStorage.getItem('myAddress'))
    

    if (window.ethereum) {
        window.web3 = new Web3(window.ethereum);
        await window.ethereum.enable();
    } else if (window.web3) {
        window.web3 = new Web3(window.web3.currentProvider);
    } else {
        window.alert('Non-Ethereum browser detected. You should consider trying MetaMask!');
        return null;
    }
    const web3 = window.web3;
    
    const contract = new web3.eth.Contract(contractABI, contractAddress);
    const myacc = await web3.eth.getAccounts();
    const userAddress = myacc[0]
    const cnt =   await contract.methods.getTransactionCount().call({from:userAddress});
    
    return cnt;
}


async function justApprove(b,q,p,rid) {
   
const contractAddress = JSON.parse(sessionStorage.getItem('contractAddress'))
    const contractABI = JSON.parse(sessionStorage.getItem('contractABI'))
    const myAddress= JSON.parse(sessionStorage.getItem('myAddress'))
    

    if (window.ethereum) {
         window.web3 = new Web3(window.ethereum);
         await window.ethereum.enable();
     } else if (window.web3) {
         window.web3 = new Web3(window.web3.currentProvider);
     } else {
         window.alert('Non-Ethereum browser detected. You should consider trying MetaMask!');
         return null;
     }
     const web3 = window.web3;
     
     const contract = new web3.eth.Contract(contractABI, contractAddress);
     const myacc = await web3.eth.getAccounts();
     const userAddress = myacc[0]
   
   //   p = await InrToWei(p);
   //   p=web3.utils.toWei(p.toString(), 'ether'); 
     
     console.log( p , " from the justapprove ")

     const transactionCount = await contract.methods.approveTransaction(rid,b,q,p).send({ from: userAddress });
   console.log(transactionCount)
   //   return transactionCount;
    
}

function formatUnixTimestamp(timestamp) {
   const date = new Date(timestamp * 1000); // Convert to milliseconds
   const day = date.getDate().toString().padStart(2, '0');
   const month = new Intl.DateTimeFormat('en-US', { month: 'short' }).format(date);
   const year = date.getFullYear().toString().slice(-2);
   const hours = date.getHours() % 12 || 12;
   const minutes = date.getMinutes().toString().padStart(2, '0');
   const ampm = date.getHours() >= 12 ? 'PM' : 'AM';
 
   const formattedDate = `${day}-${month}-${year} ${hours}:${minutes} ${ampm}`;
   return formattedDate;
 }
 


// async function InrToWei(p){
//     let eth = await fetch('https://api.coingecko.com/api/v3/simple/price?ids=ethereum&vs_currencies=inr');
//     eth = await eth.json()       
//     eth = eth.ethereum.inr;
//     return (p/eth);
// }


async function getTransactionDetails(i){
    const contractAddress = JSON.parse(sessionStorage.getItem('contractAddress'))
    const contractABI = JSON.parse(sessionStorage.getItem('contractABI'))
    const myAddress= JSON.parse(sessionStorage.getItem('myAddress'))
    

    if (window.ethereum) {
         window.web3 = new Web3(window.ethereum);
         await window.ethereum.enable();
     } else if (window.web3) {
         window.web3 = new Web3(window.web3.currentProvider);
     } else {
         window.alert('Non-Ethereum browser detected. You should consider trying MetaMask!');
         return null;
     }
     const web3 = window.web3;
     
     const contract = new web3.eth.Contract(contractABI, contractAddress);
     const myacc = await web3.eth.getAccounts();
     const userAddress = myacc[0]
     

     const detail = await contract.methods.getTransactionDetails(i).call({from:userAddress});

     return detail;
     

}

function getMyAddress(){
   const myAddress = sessionStorage.getItem('myAddress').substring(1, sessionStorage.getItem('myAddress').length - 1);
   return myAddress;
}


function showLoading() {
//    const loadingIndicator = document.getElementById('loader');
//    loadingIndicator.style.display = 'block';
}

// Function to hide the loading indicator
function hideLoading() {
//    const loadingIndicator = document.getElementById('loader');
//    loadingIndicator.style.display = 'none';
}