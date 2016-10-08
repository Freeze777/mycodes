################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../code_snippets/euler_totient.cc \
../code_snippets/maximum_density_subgraph.cc 

CC_DEPS += \
./code_snippets/euler_totient.d \
./code_snippets/maximum_density_subgraph.d 

OBJS += \
./code_snippets/euler_totient.o \
./code_snippets/maximum_density_subgraph.o 


# Each subdirectory must supply rules for building sources it contributes
code_snippets/%.o: ../code_snippets/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: Cross G++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


