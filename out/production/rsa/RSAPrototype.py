import random
import math
import time

def egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, y, x = egcd(b % a, a)
        return (g, x - (b // a) * y, y)

def modinv(a, m):
    g, x, y = egcd(a, m)
    if g != 1:
        raise Exception('modular inverse does not exist')
    else:
        return x % m



def primefactor(n):
    for i in range(2,1+int(math.sqrt(n))):
        if n % i == 0:
            if powermod(2,i,i) == 2 or i == 2:
                if powermod(2,int(n/i),int(n/i)) == 2 or int(n/i) == 2:
                    return i,int(n/i)
                return i,primefactor(int(n/i))
            return primefactor(i),primefactor(int(n/i))
def tree(data,flat):
    for i in range(len(data)):
        if type(data[i]) == int:
            flat.append(data[i])
        else:
            tree(data[i],flat)
    return flat
def phi(n):
    flat = []
    factors = tree(primefactor(n),flat)
    final = 1
    product = 1
    for i in factors:
        product *= i


    numberfactors = [0]*product
    for i in factors:
        numberfactors[i] += 1
    for i in range(len(numberfactors)):
        if numberfactors[i] != 0:
            final *= (i-1)*(i**(numberfactors[i]-1))
    return final





def lcm(x, y):
    return int(x*y//math.gcd(x,y))



def powermod(base,exponent,modulus):
    order = bin(exponent)[2:]
    powerlist = [base]*(len(order))
    result = 1
    for i in range(1,len(powerlist)):
        powerlist[i] = (powerlist[i-1]**2) % modulus

    for i in range(len(order)):
        if order[i] == '1':
            result *= powerlist[len(order)-i-1]
            result = result % modulus
    return result


def randomprime(digits):
    for i in range(random.randint(10**(digits-1),10**digits),10**digits):
        if powermod(2,i,i) == 2:
            return i


def keygen(digits):
    digits = digits
    p = randomprime(int(digits/2))
    q = randomprime(int(digits/2))
    n = p*q
    lambdan = lcm(p-1,q-1)
    '''for e in range(random.randint(13,lambdan),lambdan):
        if gcd(e,lambdan) == 1:
            break'''
    e = 2**16+1
    d = modinv(e,lambdan)

    if len(str(n)) != digits-1:
        return keygen(digits)
    else:
        return e,n,d







def toascii(text):
    ASCII = ''
    for i in text: #converts text to ASCII, and pads 0s
        ASCII1 = str(ord(i))
        while len(ASCII1) < 3:
            ASCII1 = '0' + ASCII1
        ASCII += ASCII1
    return ASCII

def RSAE(message,e,n,d,digits): #Digits is length of two pseudorandom primes
    ASCII = toascii(message)
    blocks = ['']*(int(len(ASCII)/(digits-1))+1)
    index = 0
    for i in range(len(ASCII)): #Breaks ASCII text into blocks of size digits
        blocks[index] += ASCII[i]
        if (i+1) % (digits-1) == 0:

            index += 1
    for i in range(len(blocks)):
        blocks[i] = int(blocks[i])
    cipherblocks = [0]*len(blocks)
    for i in range(len(blocks)):
        cipherblocks[i] = powermod(blocks[i],e,n)

    decoded = [0]*len(blocks)
    for i in range(len(blocks)):
        decoded[i] = powermod(cipherblocks[i],d,n)


    for i in range(len(blocks)):
        if blocks[i] != decoded[i]:
            print(blocks,'\n\n\n',cipherblocks,'\n\n\n',decoded)
            raise Exception('Check Failed!')
    return cipherblocks

def RSAD(messageblocks,n,d):
    deciphered = [0]*len(messageblocks)
    finalblocks = ['']*len(messageblocks)
    finalmessage = ''
    for i in range(len(messageblocks)):
        deciphered[i] = powermod(messageblocks[i],d,n)

        deciphered[i] = str(deciphered[i])
        while len(deciphered[i]) < len(str(n)):
            deciphered[i] = '0' + deciphered[i]

        for j in range(0,len(deciphered[i])-2,3):
            check = chr(int(str(deciphered[i][j])+str(deciphered[i][j+1])+str(deciphered[i][j+2])))
            if check != '\x00':
                finalblocks[i] += check

    for i in range(len(finalblocks)):
        finalmessage += finalblocks[i]



    return finalmessage



#message = open('text.txt','r').read()
#keylen = 250
#keys = keygen(keylen)

#print((RSAD(RSAE(message,keys[0],keys[1],keys[2],keylen),keys[1],keys[2])))
print(randomprime(500))
